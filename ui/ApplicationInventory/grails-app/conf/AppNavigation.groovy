navigation = {
    // Declare the "app" scope, used by default in tags
    app {
     
        // A nav item pointing to HomeController, using the default action
        home(controller:'home', action: 'index')

        data (controller:'data', action: 'index')        
        
        // Items pointing to ContentController, using the specific action
        help(controller:'help', action: 'index')

    }
     
//    // Some back-end admin scaffolding stuff in a separate scope
//    admin {
//         
//        // User admin, with default screen using "search" action
//        users(controller:'userAdmin', action:'search') {
//            // Declare action alias so "create" is active for both "create" and "update" actions
//            create(action:'create', actionAliases:'update')
//        }
//    }
}