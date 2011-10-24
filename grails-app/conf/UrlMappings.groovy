class UrlMappings {

	static mappings = {
		"/$controller/$action?/$id?"{
			constraints {
				// apply constraints here
			}
		}

		"/$action?/$id?" {
            controller = 'folksonomy'
        }
        "500"(view:'/error')
	}
}
