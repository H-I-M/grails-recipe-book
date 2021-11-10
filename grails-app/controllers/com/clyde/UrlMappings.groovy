package com.clyde

class UrlMappings {

    static mappings = {
        "/$controller/$action?/$id?(.$format)?"{
            constraints {
                // apply constraints here
            }
        }

        '/recipe' (resources: 'recipe') {
            collection {
                '/search'(controller: 'recipe', action: 'search')
            }
        }

        "/"(view:"/index")
        "500"(view:'/error')
        "404"(view:'/notFound')
    }
}
