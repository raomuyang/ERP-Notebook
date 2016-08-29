package org.jufe.erp.utils;

import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.Mongo;
import com.mongodb.WriteConcern;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import org.apache.log4j.AppenderSkeleton;
import org.apache.log4j.Level;
import org.apache.log4j.MDC;
import org.apache.log4j.PatternLayout;
import org.apache.log4j.spi.LoggingEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.authentication.UserCredentials;
import org.springframework.data.mongodb.core.MongoTemplate;

public class MongoLog4jAppender extends AppenderSkeleton {
    public static final String LEVEL = "level";
    public static final String NAME = "name";
    public static final String APP_ID = "applicationId";
    public static final String TIMESTAMP = "timestamp";
    public static final String PROPERTIES = "properties";
    public static final String TRACEBACK = "traceback";
    public static final String MESSAGE = "message";
    public static final String YEAR = "year";
    public static final String MONTH = "month";
    public static final String DAY = "day";
    public static final String HOUR = "hour";
    protected String host = "localhost";
    protected int port = 27017;
    protected String database = "logs";
    protected String collectionPattern = "%c";
    protected PatternLayout collectionLayout;
    protected String applicationId;
    protected WriteConcern warnOrHigherWriteConcern;
    protected WriteConcern infoOrLowerWriteConcern;
    protected Mongo mongo;
    protected DB db;
    protected String username;
    protected String password;
    protected MongoTemplate mongoTemplate;

    public MongoLog4jAppender() {
        this.collectionLayout = new PatternLayout(this.collectionPattern);
        this.applicationId = System.getProperty("APPLICATION_ID", (String)null);
        this.warnOrHigherWriteConcern = WriteConcern.SAFE;
        this.infoOrLowerWriteConcern = WriteConcern.NORMAL;
    }

    public MongoLog4jAppender(boolean isActive) {
        super(isActive);
        this.collectionLayout = new PatternLayout(this.collectionPattern);
        this.applicationId = System.getProperty("APPLICATION_ID", (String)null);
        this.warnOrHigherWriteConcern = WriteConcern.SAFE;
        this.infoOrLowerWriteConcern = WriteConcern.NORMAL;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getHost() {
        return this.host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return this.port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getDatabase() {
        return this.database;
    }

    public void setDatabase(String database) {
        this.database = database;
    }

    public String getCollectionPattern() {
        return this.collectionPattern;
    }

    public void setCollectionPattern(String collectionPattern) {
        this.collectionPattern = collectionPattern;
        this.collectionLayout = new PatternLayout(collectionPattern);
    }

    public String getApplicationId() {
        return this.applicationId;
    }

    public void setApplicationId(String applicationId) {
        this.applicationId = applicationId;
    }

    public void setWarnOrHigherWriteConcern(String wc) {
        this.warnOrHigherWriteConcern = WriteConcern.valueOf(wc);
    }

    public String getWarnOrHigherWriteConcern() {
        return this.warnOrHigherWriteConcern.toString();
    }

    public String getInfoOrLowerWriteConcern() {
        return this.infoOrLowerWriteConcern.toString();
    }

    public void setInfoOrLowerWriteConcern(String wc) {
        this.infoOrLowerWriteConcern = WriteConcern.valueOf(wc);
    }

    protected void connectToMongo() throws UnknownHostException {
        this.mongo = new Mongo(this.host, this.port);
        if(this.username != null && this.password != null){
            this.mongoTemplate = new MongoTemplate(this.mongo, this.database, new UserCredentials(this.username, this.password));
        }
//        this.db = this.mongo.getDB(this.database);
        this.db = this.mongoTemplate.getDb();
    }

    protected void append(LoggingEvent event) {
        if(null == this.db) {
            try {
                this.connectToMongo();
            } catch (UnknownHostException var9) {
                throw new RuntimeException(var9.getMessage(), var9);
            }
        }

        BasicDBObject dbo = new BasicDBObject();
        if(null != this.applicationId) {
            dbo.put("applicationId", this.applicationId);
            MDC.put("applicationId", this.applicationId);
        }

        dbo.put("name", event.getLogger().getName());
        dbo.put("level", event.getLevel().toString());
        Calendar tstamp = Calendar.getInstance();
        tstamp.setTimeInMillis(event.getTimeStamp());
        dbo.put("timestamp", tstamp.getTime());
        Map props = event.getProperties();
        if(null != props && !props.isEmpty()) {
            BasicDBObject traceback = new BasicDBObject();
            Iterator now = props.entrySet().iterator();

            while(now.hasNext()) {
                Entry coll = (Entry)now.next();
                traceback.put(coll.getKey().toString(), coll.getValue().toString());
            }

            dbo.put("properties", traceback);
        }

        String[] traceback1 = event.getThrowableStrRep();
        if(null != traceback1 && traceback1.length > 0) {
            BasicDBList now1 = new BasicDBList();
            now1.addAll(Arrays.asList(traceback1));
            dbo.put("traceback", now1);
        }

        dbo.put("message", event.getRenderedMessage());
        Calendar now2 = Calendar.getInstance();
        MDC.put("year", Integer.valueOf(now2.get(1)));
        MDC.put("month", String.format("%1$02d", new Object[]{Integer.valueOf(now2.get(2) + 1)}));
        MDC.put("day", String.format("%1$02d", new Object[]{Integer.valueOf(now2.get(5))}));
        MDC.put("hour", String.format("%1$02d", new Object[]{Integer.valueOf(now2.get(11))}));
        String coll1 = this.collectionLayout.format(event);
        MDC.remove("year");
        MDC.remove("month");
        MDC.remove("day");
        MDC.remove("hour");
        if(null != this.applicationId) {
            MDC.remove("applicationId");
        }

        WriteConcern wc;
        if(event.getLevel().isGreaterOrEqual(Level.WARN)) {
            wc = this.warnOrHigherWriteConcern;
        } else {
            wc = this.infoOrLowerWriteConcern;
        }

        this.db.getCollection(coll1).insert(dbo, wc);
    }

    public void close() {
        if(this.mongo != null) {
            this.mongo.close();
        }

    }

    public boolean requiresLayout() {
        return true;
    }
}
