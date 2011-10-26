class UrlMappings {

	static mappings = {
		"/$controller/$action?/$id?"{
			constraints {
				// apply constraints here
			}
		}
        "/tags/$action?/$id?" {
            controller = 'folksonomy'
        }
		"/" (controller:'folksonomy',action:'list')
        "500"(view:'/error')
	}
}
