class UrlMappings {

	static mappings = {
		"/$controller/$action?/$id?"{
			constraints {
				// apply constraints here
			}
		}
        "/tag/$action?/$id?" {
            controller = 'folksonomy'
        }
		"/" (controller:'folksonomy',action:'list')
        "500"(view:'/error')
	}
}
