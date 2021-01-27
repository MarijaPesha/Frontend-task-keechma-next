(ns app.ui.components.navbar
  (:require [helix.dom :as d]
            [helix.core :as hx :refer [<> $]]
            [helix.hooks :as hooks]
            [keechma.next.controllers.router :as router]
            [keechma.next.helix.core :refer [with-keechma dispatch call use-sub]]
            [keechma.next.helix.lib :refer [defnc]]
            [oops.core :refer [ocall oget]]
            [app.ui.components.theme-toggler :refer [ThemeToggler]]))

(defnc NavbarRenderer [props]
       (d/div {:className "dark:bg-black bg-gray-900 w-full text-gray-100 h-16 py-0 fixed md:relative top-0 z-10 flex"}
              (d/div {:className "w-full md:w-1/2 mx-auto flex justify-around items-center font-open-sans text-lg"}
                     (d/a {:className "flex items-center justify-center flex-col lg:flex-row lg:justify-around border-b-4  dark:border-black border-gray-900 dark:hover:border-lime-300 hover:border-lime-300  pt-0 md:pt-1 transition ease-in-out duration-200"
                           :href      (router/get-url props :router {:page "home"})}

                          (d/p "Very Big")
                          (d/p {:className "lg:ml-2 text-xl font-bold"}
                               "News"))
                     ;; TASK DRY
                     (d/a {:className "border-b-4 dark:border-black border-gray-900 dark:hover:border-lime-300 hover:border-lime-300 pt-1 transition ease-in-out duration-200"
                           :href      (router/get-url props :router {:page "home" :subpage "tech"})}
                          "Tech")
                     (d/a {:className "border-b-4 dark:border-black border-gray-900 dark:hover:border-lime-300 hover:border-lime-300 pt-1 transition ease-in-out duration-200"
                           :href      (router/get-url props :router {:page "home" :subpage "education"})}
                          "Education")
                     (d/a {:className "border-b-4 dark:border-black border-gray-900 dark:hover:border-lime-300 hover:border-lime-300 pt-1 transition ease-in-out duration-200"
                           :href      (router/get-url props :router {:page "home" :subpage "sport"})}
                          "Sport")
                     ($ ThemeToggler)
                     )
              ))

(def Navbar (with-keechma NavbarRenderer))