class UrlMappings {

	static mappings = {
		"/$controller/$action?/$id?"{
			constraints {
				// apply constraints here
			}
		}
        "/tag/$tagName?" {
            controller = 'folksonomy'
            action = 'tag'
        }
		"/$action?/$id?" {
            controller = 'folksonomy'
        }
        "500"(view:'/error')
	}
}
