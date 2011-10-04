import folksonomy.Tag

class BootStrap {

    def init = { servletContext ->
        if(Tag.count()) {
            new Tag(name:'java').save()
        }
    }
    def destroy = {
    }
}
