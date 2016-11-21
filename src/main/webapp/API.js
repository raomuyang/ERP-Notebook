

var domain = ""
var store = "http://erp-qos.atomicer.cn"

function obj_store(data, onSuccess, onError){
		//[获取CDN主服务位置] /rest/obj-store
		$.ajax({
				 url: domain + "/rest/obj-store",
				 type: "GET",
				 success: onSuccess,
				 error: onError
		})
}


function about_info_update_join(data, token, onSuccess, onError){
		//[更新协会“加入我们”] /rest/about/info/update-join
		//[参数示例] ------- 
		//"江西财经大学ERP协会-加入我们[更新]"

		$.ajax({
				 url: domain + "/rest/about/info/update-join",
				 type: "POST",
				 contentType: "application/json",
				 token: token,
				 data: data,
				 success: onSuccess,
				 error: onError
		})
}


function about_info_update_org_staruct(data, token, onSuccess, onError){
		//[更新协会“组织架构”] /rest/about/info/update-join
		//[参数示例] ------- 
		//"江西财经大学ERP协会-组织架构[更新]"

		$.ajax({
				 url: domain + "/rest/about/info/update-org-staruct",
				 type: "POST",
				 contentType: "application/json",
				 token: token,
				 data: data,
				 success: onSuccess,
				 error: onError
		})
}


function about_info_update_intro(data, token, onSuccess, onError){
		//[更新协会介绍] /rest/about/info/update-intro
		//[参数示例] ------- 
		//"江西财经大学-介绍[更新]"

		$.ajax({
				 url: domain + "/rest/about/info/update-intro",
				 type: "POST",
				 contentType: "application/json",
				 token: token,
				 data: data,
				 success: onSuccess,
				 error: onError
		})
}


function about_info_update(data, token, onSuccess, onError){
		//[更新协会信息] /rest/about/info/update
		//[参数示例] ------- 
		//{
		//  "tel":"18146713221",
		//  "intro":"江西财经大学ERP协会",
		//  "joinUs":"欢迎加入",
		//  "org":"组织架构"
		//}

		$.ajax({
				 url: domain + "/rest/about/info/update",
				 type: "POST",
				 contentType: "application/json",
				 token: token,
				 data: data,
				 success: onSuccess,
				 error: onError
		})
}


function about_info_update_tel(data, token, onSuccess, onError){
		//[更新协会电话] /rest/about/info/update-tel
		//[参数示例] ------- 
		//"18146713221"

		$.ajax({
				 url: domain + "/rest/about/info/update-tel",
				 type: "POST",
				 contentType: "application/json",
				 token: token,
				 data: data,
				 success: onSuccess,
				 error: onError
		})
}


function about_info_joinus( onSuccess, onError){
		//[获取“加入我们”] /rest/about/info/joinus
		

		$.ajax({
				 url: domain + "/rest/about/info/joinus",
				 type: "GET",
				 success: onSuccess,
				 error: onError
		})
}


function about_info_org_struct(onSuccess, onError){
		//[获取“加入组织架构”] /rest/about/info/org-struct
		

		$.ajax({
				 url: domain + "/rest/about/info/org-struct",
				 type: "GET",
				 success: onSuccess,
				 error: onError
		})
}


function about_info_base_info(onSuccess, onError){
		//[获取主页基本信息] /rest/about/info/base-info
		

		$.ajax({
				 url: domain + "/rest/about/info/base-info",
				 type: "GET",
				 success: onSuccess,
				 error: onError
		})
}


function about_info_intro( onSuccess, onError){
		//[获取介绍信息] /rest/about/info/intro
		

		$.ajax({
				 url: domain + "/rest/about/info/intro",
				 type: "GET",
				 success: onSuccess,
				 error: onError
		})
}


function about_info_tel( onSuccess, onError){
		//[获取协会联系电话] /rest/about/info/tel
		

		$.ajax({
				 url: domain + "/rest/about/info/tel",
				 type: "GET",
				 success: onSuccess,
				 error: onError
		})
}


function about_mshow_upload_image(data, token, onSuccess, onError){
		//[上传新图片] /rest/about/mshow/upload-image
		//[参数示例] ------- 
		//{
		// overrideContentType=true,
		// encoding=multipart/form-data,
		// items=[
				// 	{
				//		 name=image, type=File
				// }
		// ]}

		$.ajax({
				 url: domain + "/rest/about/mshow/upload-image",
				 type: "POST",
				 contentType: "multipart/form-data",
				 token: token,
				 data: data,
				 success: onSuccess,
				 error: onError
		})
}


function about_mshow_upload_video(data, token, onSuccess, onError){
		//[上传新视频] /rest/about/mshow/upload-video
		//[参数示例] ------- 
		//{overrideContentType=true,
		// 	encoding=multipart/form-data,
		// 	items=[{name=video, type=File}]
		// }

		$.ajax({
				 url: domain + "/rest/about/mshow/upload-video",
				 type: "POST",
				 contentType: "multipart/form-data",
				 token: token,
				 data: data,
				 success: onSuccess,
				 error: onError
		})
}


function about_mshow_delete_image(data, token, onSuccess, onError){
		//[删除历史图片] /rest/about/mshow/delete-image
		//[参数示例] ------- 
		//"['http://test1']"

		$.ajax({
				 url: domain + "/rest/about/mshow/delete-image",
				 type: "DELETE",
				 contentType: "application/json",
				 token: token,
				 data: data,
				 success: onSuccess,
				 error: onError
		})
}


function about_mshow_delete_video(data, token, onSuccess, onError){
		//[删除历史视频] /rest/about/mshow/delete-video
		//[参数示例] ------- 
		//"['http://test-video-up']" js数组

		$.ajax({
				 url: domain + "/rest/about/mshow/delete-video",
				 type: "DELETE",
				 contentType: "application/json",
				 token: token,
				 data: data,
				 success: onSuccess,
				 error: onError
		})
}


function about_mshow_update_iurls(data, token, onSuccess, onError){
		//[更新主页图片] /rest/about/mshow/update-iurls
		//[参数示例] ------- 
		//"['http://test_image3','http://test4']"

		$.ajax({
				 url: domain + "/rest/about/mshow/update-iurls",
				 type: "POST",
				 contentType: "application/json",
				 token: token,
				 data: data,
				 success: onSuccess,
				 error: onError
		})
}


function about_mshow_update_words(data, token, onSuccess, onError){
		//[更新主页寄语] /rest/about/mshow/update-words
		//[参数示例] ------- 
		//"首页寄语"

		$.ajax({
				 url: domain + "/rest/about/mshow/update-words",
				 type: "POST",
				 contentType: "application/json",
				 token: token,
				 data: data,
				 success: onSuccess,
				 error: onError
		})
}


function about_mshow_update(data, token, onSuccess, onError){
		//[更新主页展示] /rest/about/mshow/update
		//[参数示例] ------- 
		//{
		//  "iurls":["http://test1","http://test2"],
		//  "vurl":"http://testvideourl",
		//  "words":"主页寄语"
		//  
		//}

		$.ajax({
				 url: domain + "/rest/about/mshow/update",
				 type: "POST",
				 contentType: "application/json",
				 token: token,
				 data: data,
				 success: onSuccess,
				 error: onError
		})
}


function about_mshow_update_video(data, token, onSuccess, onError){
		//[更新主页视频] /rest/about/mshow/update-video
		//[参数示例] ------- 
		//"http://test-video-up"

		$.ajax({
				 url: domain + "/rest/about/mshow/update-video",
				 type: "POST",
				 contentType: "application/json",
				 token: token,
				 data: data,
				 success: onSuccess,
				 error: onError
		})
}


function about_mshow_main( onSuccess, onError){
		//[获取主页展示] /rest/about/mshow/main
		

		$.ajax({
				 url: domain + "/rest/about/mshow/main",
				 type: "GET",
				 success: onSuccess,
				 error: onError
		})
}


function auth_policy_savepolicy(data, token, onSuccess, onError){
		//[保存授权政策|没有则新增] /rest/auth/policy/save-policy
		//[参数示例] ------- 
		//{
		//  "id":"test",
		//  "auth":{
		//    "READ":true,
		//    "WRITE":true,
		//    "UPDATE":true,
		//    "DELETE":true
		//  }
		//}

		$.ajax({
				 url: domain + "/rest/auth/policy/save-policy",
				 type: "PUT",
				 contentType: "application/json",
				 token: token,
				 data: data,
				 success: onSuccess,
				 error: onError
		})
}


function auth_policy_delete(policyId, token, onSuccess, onError){
		//[删除授权政策]  /rest/auth/policy/delete
		//[参数示例] ------- 
		//"policyId"

		$.ajax({
				 url: domain + "/rest/auth/policy/delete",
				 type: "DELETE",
				 contentType: "application/json",
				 token: token,
				 data: policyId,
				 success: onSuccess,
				 error: onError
		})
}


function auth_policy_get_policyinfo(policyId, token, onSuccess, onError){
		//[获取政策信息] /rest/auth/policy/policy-info/{policyId}
		

		$.ajax({
				 url: domain + "/rest/auth/policy/policy-info/" + policyId,
				 type: "GET",
				 token: token,
				 success: onSuccess,
				 error: onError
		})
}


function auth_role_update_role(data, token, onSuccess, onError){
		//[保存角色|没有则新建] /rest/auth/role/update-role
		//[参数示例] ------- 
		//{
		//  "id":"controller",
		//  "level":2,
		//  "roleName":"协会管理者",
		//  "roleDes":"拥有审核、管理的权限,可以添加、维护角色、权限等信息"
		//}

		$.ajax({
				 url: domain + "/rest/auth/role/update-role",
				 type: "POST",
				 contentType: "application/json",
				 token: token,
				 data: data,
				 success: onSuccess,
				 error: onError
		})
}


function auth_role_delete(roleId, token, onSuccess, onError){
		//[删除角色] /rest/auth/role/delete
		//[参数示例] ------- 
		//"roleId"

		$.ajax({
				 url: domain + "/rest/auth/role/delete",
				 type: "DELETE",
				 contentType: "application/json",
				 token: token,
				 data: roleId,
				 success: onSuccess,
				 error: onError
		})
}


function auth_role_update_role_info(data, token, onSuccess, onError){
		//[更新角色] /rest/auth/role/update-role-info
		//[参数示例] ------- JSON对象
		//{
		//  "id":"controller",
		//  "level":3,
		//  "roleName":"协会管理者",
		//  "roleDes":"拥有审核、管理的权限,可以添加、维护角色、权限等信息"
		//}

		$.ajax({
				 url: domain + "/rest/auth/role/update-role-info",
				 type: "POST",
				 contentType: "application/json",
				 token: token,
				 data: data,
				 success: onSuccess,
				 error: onError
		})
}


function auth_role_add_role(data, token, onSuccess, onError){
		//[添加角色] /rest/auth/role/add-role
		//[参数示例] -------  JSON对象
		//{
		//  "id":"controller",
		//  "level":3,
		//  "roleName":"协会管理者",
		//  "roleDes":"拥有审核、管理的权限,可以添加、维护角色、权限等信息"
		//}

		$.ajax({
				 url: domain + "/rest/auth/role/add-role",
				 type: "PUT",
				 contentType: "application/json",
				 token: token,
				 data: data,
				 success: onSuccess,
				 error: onError
		})
}


function auth_role_get_role_infos(roleIds, onSuccess, onError){
		//[获取多个角色信息] /rest/auth/role/role-infos/{['id1','id2']}
		//[参数示例] ------ string类型
		//  "['id1','id2',"id3"]"
		$.ajax({
				 url: domain + "/rest/auth/role/role-infos/" + roleIds,
				 type: "GET",
				 success: onSuccess,
				 error: onError
		})
}


function auth_role_get_role_infos_all( onSuccess, onError){
		//[获取所有角色] /rest/auth/role/role-infos/all
		

		$.ajax({
				 url: domain + "/rest/auth/role/role-infos/all",
				 type: "GET",
				 success: onSuccess,
				 error: onError
		})
}


function auth_role_get_role_info_by_id(roleId, onSuccess, onError){
		//[获取角色by-id] /rest/auth/role/role-info/{roleId}
		//[参数示例] --- string
		// "roleId"

		$.ajax({
				 url: domain + "/rest/auth/role/role-info/" + roleId,
				 type: "GET",
				 success: onSuccess,
				 error: onError
		})
}


function auth_policy_save_policy(data, token, onSuccess, onError){
		//[保存授权政策|没有则新增] /rest/auth/policy/save-policy
		//[参数示例] ------- JSON对象
		//{
		//  "id":"test",
		//  "auth":{
		//    "READ":true,
		//    "WRITE":true,
		//    "UPDATE":true,
		//    "DELETE":true
		//  }
		//}

		$.ajax({
				 url: domain + "/rest/auth/policy/save-policy",
				 type: "PUT",
				 contentType: "application/json",
				 token: token,
				 data: data,
				 success: onSuccess,
				 error: onError
		})
}



function auth_role_policy_get_valid_policies(roleId, token, onSuccess, onError){
		//[获取授权政策信息| 当前有效的] /rest/auth/role-policy/policies/valid/roleid/{roleId}
		

		$.ajax({
				 url: domain + "/rest/auth/role-policy/policies/valid/roleid/" + roleId,
				 type: "GET",
				 token: token,
				 success: onSuccess,
				 error: onError
		})
}


function auth_role_policy_get_valid_policies(roleId, date, token, onSuccess, onError){
		//[获取"授权政策"信息| 某日期前有效的] /rest/auth/role-policy/policies/valid/roleid/{roleId}/date/{date}
		//[参数示例] --- string
		// "roleId"
		// "xxxx-xx-xx"
		var path = "/rest/auth/role-policy/policies/valid/roleid/{roleId}/date/{date}".replace("{roleId}", roleId).replace("{date}", date)
		$.ajax({
				 url: domain + path,
				 type: "GET",
				 token: token,
				 success: onSuccess,
				 error: onError
		})
}


function auth_role_policy_get_policies(roleId, token, onSuccess, onError){
		//[获取授权政策信息| 通过角色ID] /rest/auth/role-policy/policies/roleid/{roleId}
		//[参数示例]---string
		// "roleId"

		$.ajax({
				 url: domain + "/rest/auth/role-policy/policies/roleid/" + roleId,
				 type: "GET",
				 token: token,
				 success: onSuccess,
				 error: onError
		})
}


function auth_role_policy_get_all( token, onSuccess, onError){
		//[获取角色-授权| 所有] /rest/auth/role-policy/info/all
		

		$.ajax({
				 url: domain + "/rest/auth/role-policy/info/all",
				 type: "GET",
				 token: token,
				 success: onSuccess,
				 error: onError
		})
}


function auth_role_policy_get_valid_info(roleId, date, token, onSuccess, onError){
		//[获取角色-授权| 某日期前有效的] /rest/auth/role-policy/info/valid/roleid/{roleId}/date/{date}
		//[参数示例]---string
		// "roleid" "xxxx-xx-xx"
		path = "/rest/auth/role-policy/info/valid/roleid/{roleId}/date/{date}".replace("{roleId}", roleId).replace("{date}", date)
		$.ajax({
				 url: domain + path,
				 type: "GET",
				 token: token,
				 success: onSuccess,
				 error: onError
		})
}


function auth_role_policy_get_info_by_roleid_and_policyid(roleId, policyId, token, onSuccess, onError){
		//[获取角色-授权|通过角色-政策ID] /rest/auth/role-policy/info/roleid/{roleId}/policyid/{policyId}
		//[参数示例] -------  string
		// "roleid"  "policyid"

		var path = "/rest/auth/role-policy/info/roleid/{roleId}/policyid/{policyId}"
										.replace("{roleId}", roleId).replace("{policyId}", policyId)
		$.ajax({
				 url: domain + path,
				 type: "GET",
				 token: token,
				 success: onSuccess,
				 error: onError
		})
}


function auth_user_role_delete(id, token, onSuccess, onError){
		//[删除"用户-角色"绑定] /rest/auth/user-role/update
		//[参数示例] -------  string
		//"580e3e0e7e44b83512c7b4c8"

		$.ajax({
				 url: domain + "/rest/auth/user-role/delete",
				 type: "DELETE",
				 contentType: "application/json",
				 token: token,
				 data: id,
				 success: onSuccess,
				 error: onError
		})
}


function auth_user_role_update(data, token, onSuccess, onError){
		//[更新用户-角色绑定| 没有则创建] /rest/auth/user-role/update
		//[参数示例] ------- JSON对象
		//{
		//  "userId":"raomengnan@qq.com",
		//  "roleId":"controller",
		//  "termD":${timestamp()}
		//}

		$.ajax({
				 url: domain + "/rest/auth/user-role/update",
				 type: "POST",
				 contentType: "application/json",
				 token: token,
				 data: data,
				 success: onSuccess,
				 error: onError
		})
}


function auth_user_role_get_infos_by_userid(userId, onSuccess, onError){
		//[获取用户-角色绑定| 通过用户ID] /rest/auth/user-role/infos/userid/{userid}/
		
		var path = "/rest/auth/user-role/infos/userid/{userid}/".replace("{userid}", userId)
		$.ajax({
				 url: domain + path,
				 type: "GET",
				 success: onSuccess,
				 error: onError
		})
}


function auth_user_role_get_infos_by_roleid(roleId, onSuccess, onError){
		//[获取用户-角色绑定| 通过角色ID] /rest/auth/user-role/infos/roleid/{roleid}/
		
		var path = "/rest/auth/user-role/infos/roleid/{roleid}/".replace("{roleid}", roleId)
		$.ajax({
				 url: domain + path,
				 type: "GET",
				 success: onSuccess,
				 error: onError
		})
}


function auth_user_role_get_users_by_roleid(roleId, onSuccess, onError){
		//[获取用户| 通过角色ID] /rest/auth/user-role/users/roleid/{roleid}
		
		var path = "/rest/auth/user-role/users/roleid/{roleid}".replace("{roleid}", roleId);
		$.ajax({
				 url: domain + path,
				 type: "GET",
				 success: onSuccess,
				 error: onError
		})
}


function auth_user_role_get_roles_by_userid(userId, onSuccess, onError){
		//[获取角色| 通过用户ID] /rest/auth/user-role/roles/userid/{userid}/
		
		var path = "/rest/auth/user-role/roles/userid/{userid}/".replace("{userid}", userId)
		$.ajax({
				 url: domain + path,
				 type: "GET",
				 success: onSuccess,
				 error: onError
		})
}


function news_image_upload_image(data, token, onSuccess, onError){
		//[上传文章图片] /rest/news/image/upload-image
		//[参数示例] ------- multipart/form-data
		// newsId=xxx, intro=xxx, imageFile=xxxx

		$.ajax({
				 url: domain + "/rest/news/image/upload-image",
				 type: "POST",
				 contentType: "multipart/form-data",
				 token: token,
				 data: data,
				 success: onSuccess,
				 error: onError
		})
}


function news_create(data, token, onSuccess, onError){
		//[创建文章] /rest/news/create
		//[参数示例] ------- JSON对象
		//{
		//  "title":"测试文章1",
		//  "context":"hhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhh",
		//  "finish":false
		//}

		$.ajax({
				 url: domain + "/rest/news/create",
				 type: "PUT",
				 contentType: "application/json",
				 token: token,
				 data: data,
				 success: onSuccess,
				 error: onError
		})
}


function news_image_delete_by_newsid(newsId, token, onSuccess, onError){
		//[删除文章图片| 通过newsId] /rest/news/image/delete-by-newsid
		//[参数示例] ------- 
		//"580e43147e44b83512c7b4cb"

		$.ajax({
				 url: domain + "/rest/news/image/delete-by-newsid",
				 type: "DELETE",
				 contentType: "application/json",
				 token: token,
				 data: newsId,
				 success: onSuccess,
				 error: onError
		})
}


function news_image_delete_by_url(url, token, onSuccess, onError){
		//[删除文章图片| 通过url] /rest/news/image/delete-by-url
		//[参数示例] ------- string
		//  "/resource/news/20161025/580e43147e44b83512c7b4cb/580ee5857e44b8351278b9bf.png"

		$.ajax({
				 url: domain + "/rest/news/image/delete-by-url",
				 type: "DELETE",
				 contentType: "application/json",
				 token: token,
				 data: url,
				 success: onSuccess,
				 error: onError
		})
}


function news_update(data, token, onSuccess, onError){
		//[更新文章] /rest/news/update
		//[参数示例] ------- JSON对象
		//{
		//  "id":"580e43147e44b83512c7b4cb",
		//  "title":"测试文章更新",
		//  "context":"dddddddddddd",
		//  "finish":true
		//}

		$.ajax({
				 url: domain + "/rest/news/update",
				 type: "POST",
				 contentType: "application/json",
				 token: token,
				 data: data,
				 success: onSuccess,
				 error: onError
		})
}


function news_image_update_intro(data, token, onSuccess, onError){
		//[更新文章图片介绍] /rest/news/image/update-intro
		//[参数示例] ------- JSON对象
		//{
		//  "id":"580ee5857e44b8351278b9bf",
		//  "intro":"hhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhh"
		//} 

		$.ajax({
				 url: domain + "/rest/news/image/update-intro",
				 type: "POST",
				 contentType: "application/json",
				 token: token,
				 data: data,
				 success: onSuccess,
				 error: onError
		})
}


function news_update_status(data, token, onSuccess, onError){
		//[更新文章状态] /rest/news/update-status
		//[参数示例] ------- JSON对象
		//{
		//  "id":"580e43147e44b83512c7b4cb",
		//  "finish":false
		//}

		$.ajax({
				 url: domain + "/rest/news/update-status",
				 type: "POST",
				 contentType: "application/json",
				 token: token,
				 data: data,
				 success: onSuccess,
				 error: onError
		})
}


function news_get_page(psize, pno, onSuccess, onError){
		//[获取公开文章] /rest/news/page/psize/{psize}/pno/{pno}
		//[参数示例] ------- string or int


		var path = "/rest/news/page/psize/{psize}/pno/{pno}".replace("{psize}", psize).replace("{pno}", pno)
		$.ajax({
				 url: domain + path,
				 type: "GET",
				 success: onSuccess,
				 error: onError
		})
}


function news_search_by_keyword(keyword, onSuccess, onError){
		//[获取公开文章列表| 通过关键字] /rest/news/keyw/{keyw}/
		//[参数示例] -------  string
		// "关键字"

		$.ajax({
				 url: domain + "/rest/news/keyw/{keyw}".replace("{keyw}", keyword),
				 type: "GET",
				 success: onSuccess,
				 error: onError
		})
}


function news_search_by_title(title, onSuccess, onError){
		//[获取公开文章列表| 通过标题] /rest/news/title/{title}/
		//[参数示例] ------- string
		// "试文章1"


		var path = "/rest/news/title/{title}/".replace("{title}", title)
		$.ajax({
				 url: domain + path,
				 type: "GET",
				 success: onSuccess,
				 error: onError
		})
}


function news_search_by_authorid(authorid, onSuccess, onError){
		//[获取公开文章列表| 通过用户ID] /rest/news/authorid/{authorid}/
		//[参数示例] ------- string
		// "raomengnan@qq.com"

		var path = "/rest/news/authorid/{authorid}/".replace("{authorid}", authorid)
		$.ajax({
				 url: domain + path,
				 type: "GET",
				 success: onSuccess,
				 error: onError
		})
}


function news_search_by_author(authorname, onSuccess, onError){
		//[获取公开文章列表| 通过用户名] /rest/news/author/{author}
		//[参数示例] ------- string
		// "饒"

		var path = "/rest/news/author/{author}".replace("{author}", authorname)
		$.ajax({
				 url: domain + path,
				 type: "GET",
				 success: onSuccess,
				 error: onError
		})
}


function news_get_by_id(id, token, onSuccess, onError){
		//[获取文章内容] /rest/news/{newsId}
		//[参数示例] ------- string
		//"580e43147e44b83512c7b4cb"

		var path = "/rest/news/{newsId}".replace("{newsId}", id);
		$.ajax({
				 url: domain + path,
				 type: "GET",
				 token: token,
				 success: onSuccess,
				 error: onError
		})
}


function news_image_get_by_newsid_580e43147e44b83512c7b4cb(newsId, token, onSuccess, onError){
		//[获取文章图片| 列表] /rest/news/image/newid/{newid}
		//[参数示例] ------- 
		// "580e43147e44b83512c7b4cb"

		var path = "/rest/news/image/newid/{newid}".replace("{newsid}", newsId);
		$.ajax({
				 url: domain + path,
				 type: "GET",
				 token: token,
				 success: onSuccess,
				 error: onError
		})
}


function news_image_get_all(pno, psize, token, onSuccess, onError){
		//[获取文章图片| 所有，分页展示] /rest/news/image/psize/{psize}/pno/{pno}

		var path = "/rest/news/image/psize/{psize}/pno/{pno}".replace("{psize}", psize).replace("{pno}", pno)
		$.ajax({
				 url: domain + path,
				 type: "GET",
				 token: token,
				 success: onSuccess,
				 error: onError
		})
}


function news_no_finish( token, onSuccess, onError){
		//[获取未完成文章] /rest/news/no-finish
		//[参数示例] ------- 
		//{
		//  "title":"测试文章1",
		//  "context":"hhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhh"
		//}

		$.ajax({
				 url: domain + "/rest/news/no-finish",
				 type: "GET",
				 token: token,
				 success: onSuccess,
				 error: onError
		})
}


function news_no_finish(token, psize, pno, onSuccess, onError){
		//[获取未完成文章| 分页] /rest/news/no-finish/page/psize/{psize}/pno/{pno}

		var path = "/rest/news/no-finish/page/psize/{psize}/pno/{pno}".replace("{psize}", psize).replace("{pno}", pno)
		$.ajax({
				 url: domain + path,
				 type: "GET",
				 token: token,
				 data: data,
				 success: onSuccess,
				 error: onError
		})
}


function photo_wall_upload_photo(data, token, onSuccess, onError){
		//[上传图片] /rest/photo/photo-wall/upload-photo
		//[参数示例] -------  multipart/form-data
		//userId=xxx, grade=2011, userName=xxx,imageFile=xxx(type=File)

		$.ajax({
				 url: domain + "/rest/photo/photo-wall/upload-photo",
				 type: "POST",
				 contentType: "multipart/form-data",
				 token: token,
				 data: data,
				 success: onSuccess,
				 error: onError
		})
}


function photo_wall_delete(photoId, token, onSuccess, onError){
		//[删除照片] /rest/photo/photo-wall/delete
		//[参数示例] ------- 
		//"580f09b67e44b86b37e10f33"

		$.ajax({
				 url: domain + "/rest/photo/photo-wall/delete",
				 type: "DELETE",
				 contentType: "application/json",
				 token: token,
				 data: photoId,
				 success: onSuccess,
				 error: onError
		})
}


function photo_wall_update_photo(data, token, onSuccess, onError){
		//[更新照片] /rest/photo/photo-wall/update-photo
		//[参数示例] ------- multipart/form-data
		//{id=580f09b67e44b86b37e10f33, imageFile=xxx(type=File)

		$.ajax({
				 url: domain + "/rest/photo/photo-wall/update-photo",
				 type: "POST",
				 contentType: "multipart/form-data",
				 token: token,
				 data: data,
				 success: onSuccess,
				 error: onError
		})
}


function photo_wall_update_info(data, token, onSuccess, onError){
		//[更新照片信息] /rest/photo/photo-wall/update-info
		//[参数示例] ------- JSON对象
		//{
		//  "id":"580f09b67e44b86b37e10f33",
		//  "userId":"testdev",
		//  "grade":2011,
		//  "userName":"name"
		//}

		$.ajax({
				 url: domain + "/rest/photo/photo-wall/update-info",
				 type: "POST",
				 contentType: "application/json",
				 token: token,
				 data: data,
				 success: onSuccess,
				 error: onError
		})
}


function photo_wall_list(psize, pno, onSuccess, onError){
		//[获取照片列表| 分页] /rest/photo/photo-wall/page/psize/{psize}/pno/{pno}

		var path = "/rest/photo/photo-wall/page/psize/{psize}/pno/{pno}".replace("{psize}", psize).replace("{pno}", pno)
		$.ajax({
				 url: domain + path,
				 type: "GET",
				 success: onSuccess,
				 error: onError
		})
}


function photo_wall_list(onSuccess, onError){
		//[获取照片列表| 所有] /rest/photo/photo-wall/list

		$.ajax({
				 url: domain + "/rest/photo/photo-wall/list",
				 type: "GET",
				 success: onSuccess,
				 error: onError
		})
}


function photo_wall_list_by_grade(grade, onSuccess, onError){
		//[获取照片列表| 通过年级] /rest/photo/photo-wall/grade/{grade}
		//[参数示例] ------- int or string
		// 2013

		var path = "/rest/photo/photo-wall/grade/{grade}".replace("{grade}", grade)
		$.ajax({
				 url: domain + path,
				 type: "GET",
				 success: onSuccess,
				 error: onError
		})
}


function photo_photo_wall_search_by_username(userName, onSuccess, onError){
		//[获取照片列表| 通过用户名] /rest/photo/photo-wall/username/{username}
		//[参数示例] ------- string

		var path = "/rest/photo/photo-wall/username/{username}".replace("{username}", userName)
		$.ajax({
				 url: domain + path,
				 type: "GET",
				 success: onSuccess,
				 error: onError
		})
}


function photo_timeshaft_delete(pictureId, token, onSuccess, onError){
		//[删除时间轴照片] /rest/photo/timeshaft/delete
		//[参数示例] ------- string
		//"580f12fd7e44b86b374558aa"

		$.ajax({
				 url: domain + "/rest/photo/timeshaft/delete",
				 type: "DELETE",
				 contentType: "application/json",
				 token: token,
				 data: pictureId,
				 success: onSuccess,
				 error: onError
		})
}


function photo_timeshaft_update_info(data, token, onSuccess, onError){
		//[更新时间轴照片信息] /rest/photo/timeshaft/update-info
		//[参数示例] ------- JSON对象
		//{
		//  "id":"580f13bf7e44b86b374558ab",
		//  "intro":"testdev",
		//  "date":${timestamp()}
		//}

		$.ajax({
				 url: domain + "/rest/photo/timeshaft/update-info",
				 type: "POST",
				 contentType: "application/json",
				 token: token,
				 data: data,
				 success: onSuccess,
				 error: onError
		})
}


function photo_timeshaft_add_image(data, token, onSuccess, onError){
		//[添加时间轴照片] /rest/photo/timeshaft/add-image  [intro参数可以不携带]
		//[参数示例] ------- multipart/form-data
		//imageFile=xxx(type=File), intro=xxx

		$.ajax({
				 url: domain + "/rest/photo/timeshaft/add-image",
				 type: "POST",
				 contentType: "multipart/form-data",
				 token: token,
				 data: data,
				 success: onSuccess,
				 error: onError
		})
}


function photo_timeshaft_list(psize, pno, onSuccess, onError){
		//[获取时间轴照片] /rest/photo/timeshaft/page/psize/{psize}/pno/{pno}
		//[参数示例] ------- 

		var path = "/rest/photo/timeshaft/page/psize/{psize}/pno/{pno}".replace("{psize}", psize).replace("{pno}", pno)
		$.ajax({
				 url: domain + path,
				 type: "GET",
				 success: onSuccess,
				 error: onError
		})
}


function photo_timeshaft_get_node_info_by_id(id, onSuccess, onError){
		//[获取时间轴照片信息] /rest/photo/timeshaft/info/{id}
		//[参数示例] ------- string
		//"580f09b67e44b86b37e10f33"

		var path = "/rest/photo/timeshaft/info/{id}".replace("{id}", id)
		$.ajax({
				 url: domain + path,
				 type: "GET",
				 success: onSuccess,
				 error: onError
		})
}


function user_info_list_by_name(name, onSuccess, onError){
		//[搜索用户| 通过用户名] /rest/user/info/list/name/{username}

		var path = "/rest/user/info/list/name/{username}".replace("{username}", name)
		$.ajax({
				 url: domain + path,
				 type: "GET",
				 success: onSuccess,
				 error: onError
		})
}


function user_info_list_by_grade(grade, onSuccess, onError){
		//[搜索用户| 通过用户年级] /rest/user/info/list/grade/{grade}
		//[参数示例] ------- 

		var path = "/rest/user/info/list/grade/{grade}".replace("{grade}", grade)
		$.ajax({
				 url: domain + path,
				 type: "GET",
				 success: onSuccess,
				 error: onError
		})
}


function user_info_list_by_location(location, onSuccess, onError){
		//[搜索用户| 通过用户所在地] /rest/user/info/list/location/{location}

		var path = "/rest/user/info/list/location/{location}".replace("{location}", location)
		$.ajax({
				 url: domain + path,
				 type: "GET",
				 success: onSuccess,
				 error: onError
		})
}


function user_update(data, token, onSuccess, onError){
		//[更新用户] /rest/user/update
		//[参数示例] ------- JSON对象
		//{
		//  "id":"test1",
		//  "userName":"TestHHH",
		//  "pwd":"test",
		//  "userGrade":2011,
		//  "nowInWhere":"Shanghai"
		//}

		$.ajax({
				 url: domain + "/rest/user/update",
				 type: "POST",
				 contentType: "application/json",
				 token: token,
				 data: data,
				 success: onSuccess,
				 error: onError
		})
}


function user_update_username(data, token, onSuccess, onError){
		//[更新用户名] /rest/user/update-username
		//[参数示例] ------- 
		//{
		//  "id":"devtest",
		//  "userName":"DEVTEST"
		//}

		$.ajax({
				 url: domain + "/rest/user/update-username",
				 type: "POST",
				 contentType: "application/json",
				 token: token,
				 data: data,
				 success: onSuccess,
				 error: onError
		})
}


function user_update_head(data, token, onSuccess, onError){
		//[更新用户头像] /rest/user/update-head
		//[参数示例] ------- multipart/form-data
		//userId=test1, imageFile=xxx(type=File)

		$.ajax({
				 url: domain + "/rest/user/update-head",
				 type: "POST",
				 contentType: "multipart/form-data",
				 token: token,
				 data: data,
				 success: onSuccess,
				 error: onError
		})
}


function user_full_info_update(data, token, onSuccess, onError){
		//[更新用户完整信息] /rest/user/f-info/update
		//[参数示例] ------- 
		//{
		//  "userId":"raomengnan@qq.com",
		//  "phone":"123456",
		//  "wechat":"",
		//  "qq":"123456",
		//  "weibo":"asdf",
		//  "address":"家庭住址"
		//}

		$.ajax({
				 url: domain + "/rest/user/f-info/update",
				 type: "POST",
				 contentType: "application/json",
				 token: token,
				 data: data,
				 success: onSuccess,
				 error: onError
		})
}


function user_update_pwd(data, token, onSuccess, onError){
		//[更新用户密码] /rest/user/update-pwd
		//[参数示例] ------- JSON对象
		//{
		//  "id":"devtest",
		//  "pwd":"pwd"
		//}

		$.ajax({
				 url: domain + "/rest/user/update-pwd",
				 type: "POST",
				 contentType: "application/json",
				 token: token,
				 data: data,
				 success: onSuccess,
				 error: onError
		})
}


function user_update_location(data, token, onSuccess, onError){
		//[更新用户所在地] /rest/user/update-location
		//[参数示例] ------- 
		//{
		//  "id":"devtest",
		//  "nowInWhere":"nowInWhere"
		//}

		$.ajax({
				 url: domain + "/rest/user/update-location",
				 type: "POST",
				 contentType: "application/json",
				 token: token,
				 data: data,
				 success: onSuccess,
				 error: onError
		})
}


function user_get_info_by_id(id, onSuccess, onError){
		//[查看用户信息] /rest/user/info/{userid}/
		//[参数示例] ------- 

		var path = "/rest/user/info/{userid}/".replace("userid", id)
		$.ajax({
				 url: domain + path,
				 type: "GET",
				 success: onSuccess,
				 error: onError
		})
}


function user_get_full_info(userid, onSuccess, onError){
		//[查看用户完整信息] /rest/user/f-info/{userid}/

		var path = "/rest/user/f-info/{userid}/".replace("{userid}", userid)
		$.ajax({
				 url: domain + path,
				 type: "GET",
				 success: onSuccess,
				 error: onError
		})
}


function user_check_exist(userid, onSuccess, onError){
		//[检查ID是否存在] /rest/user/check-exist/{id}

		var path = "/rest/user/check-exist/{id}".replace("{id}", userid)
		$.ajax({
				 url: domain + path,
				 type: "GET",
				 success: onSuccess,
				 error: onError
		})
}


function user_regist(data, onSuccess, onError){
		//[注册] /rest/user/regist    (注册时代表头像的imageFile参数可以为null，但必须包含)
		//[参数示例] ------- encoding=multipart/form-data
		//id=test, userName=TestCase, pwd=test, userGrade=2013, nowInWhere=Beijing, imageFile=xxx(type=File)

		$.ajax({
				 url: domain + "/rest/user/regist",
				 type: "POST",
				 contentType: "multipart/form-data",
				 data: data,
				 success: onSuccess,
				 error: onError
		})
}


function user_full_info_delete(userId, token, onSuccess, onError){
		//[清空用户完整信息] /rest/user/f-info/delete
		//[参数示例] ------- 
		//"raomengnan@qq.com"

		$.ajax({
				 url: domain + "/rest/user/f-info/delete",
				 type: "DELETE",
				 contentType: "application/json",
				 token: token,
				 data: userId,
				 success: onSuccess,
				 error: onError
		})
}


function user_auth(data, onSuccess, onError){
		//[登陆鉴权] /rest/user/auth
		//[参数示例] ------- 
		//{"id":"userid@erp.org","pwd":"test"}

		$.ajax({
				 url: domain + "/rest/user/auth",
				 type: "POST",
				 contentType: "application/json",
				 data: data,
				 success: onSuccess,
				 error: onError
		})
}


