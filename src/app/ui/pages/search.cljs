(ns app.ui.pages.search
   (:require [helix.dom :as d]
            [helix.core :as hx :refer [$ <>]]
             
            [keechma.next.helix.core :refer [with-keechma use-sub use-meta-sub dispatch]]
            [keechma.next.controllers.pipelines :refer [get-promise]]
            [keechma.next.controllers.router :as router]
            [keechma.next.helix.lib :refer [defnc]]
            [keechma.next.helix.classified :refer [defclassified]]
             
            [app.ui.components.inputs    :refer [wrapped-input]]
            [app.ui.components.navbar :refer [Navbar]]
            [app.ui.components.header :refer [Header]]
            [app.ui.components.footer :refer [Footer]]
            [app.ui.components.preloader :refer [Preloader]]
            [keechma.next.toolbox.logging :as l]))

  
(defnc SearchRenderer [props]
  (let [search-data (use-sub props :search)]
    ( <>
      ($ Navbar)
     
     (d/div {:className "mt-16 w-2/3 mx-auto"}
        (d/form
         {
          :onSubmit (fn [e]
                      (.preventDefault e)
                      (dispatch props :search :keechma.form/submit))}
         
         (wrapped-input {:keechma.form/controller :search
                         :input/type :text
                         :input/attr :search-value
                         :placeholder "Enter keyword"})
         (d/button {:className "my-6 absolute bg-teal-500 hover:bg-teal-700 text-white font-bold py-2 px-4 rounded"} "Search")
         )
               )
     
         (d/div {:class "mt-16 w-2/3 mx-auto"}
                    (map (fn [article]
                          ;; (l/pp article)
                           (d/div {:key       (:id article)
                        :className "flex justify-start w-full border-b border-gray-500 py-6"
                        :onClick   #(router/redirect! props :router {:page "article" :id (:id article)})}  
        
                  (d/div {:className "cursor-pointer text-left ml-4"}
                    (d/h3 {:className "inline text-base md:text-xl border-b-4 border-teal-500"}
                      (:webTitle article))
                    (d/p {:className "dark:text-gray-400 text-gray-700 text-sm mt-4"}
                      (:sectionName article)
                      " | "
                      (:webPublicationDate article))))
                           )
                         search-data))
         (d/div {:className "fixed bottom-0 w-full"}
                ($ Footer)
                )
       )))

(def Search (with-keechma SearchRenderer ))
