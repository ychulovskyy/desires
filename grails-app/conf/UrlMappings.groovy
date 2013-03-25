class UrlMappings {

	static mappings = {

        name comments: "/desire/$desireid?/comment/$id?"(controller: "comment", parseRequest: true) {
            action = [POST: "create", GET: "read", PUT: "update", DELETE: "delete"]
        }

        name search: "/search"(controller: "search", parseRequest:true){
            action = [GET: "search"]
        }

        name crud: "/$controller/$id?"(parseRequest:true){
            action = [POST: "create", GET: "read", PUT: "update", DELETE: "delete"]
        }

        "/login/$action?"(controller: "login")
        "/logout/$action?"(controller: "logout")


        "/"(view:"/index")
		"500"(view:'/error')
	}
}
