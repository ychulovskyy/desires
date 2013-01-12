class UrlMappings {

	static mappings = {
        "/desire/$desireid?/comment/$action?/$id?"(controller: "comment")

        "/$controller/$action?/$id?"{
			constraints {
				// apply constraints here
			}
		}

        "/login/$action?"(controller: "login")
        "/logout/$action?"(controller: "logout")


        "/"(view:"/index")
		"500"(view:'/error')
	}
}
