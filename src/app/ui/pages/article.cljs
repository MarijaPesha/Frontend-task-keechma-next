(ns app.ui.pages.article
  (:require [helix.dom :as d]
            [helix.core :as hx :refer [$]]
            [keechma.next.helix.core :refer [with-keechma use-sub dispatch]]
            [keechma.next.controllers.router :as router]
            [keechma.next.helix.lib :refer [defnc]]
            [keechma.next.helix.classified :refer [defclassified]]
            [app.ui.components.navbar :refer [Navbar]]
            [app.ui.components.header :refer [Header]]))

(defclassified ArticleWrapper :div "font-open-sans h-screen w-screen flex flex-col bg-gray-200 dark:bg-gray-900 dark:text-gray-100 overflow-x-hidden overflow-y-scroll")

(defnc ArticleRenderer [props]
       (let [article-data (:data (use-sub props :article))]
         ($ ArticleWrapper
            ($ Navbar)
            (d/div {:className "w-full md:w-3/5 mx-auto"}
                   (d/img {:src (get-in article-data [:fields :thumbnail])
                           :className "object-cover w-full"}))
            (d/div {:class "dark:bg-gray-800 bg-gray-100 flex flex-1 flex-col items-start justify-start px-6 md:px-12 w-full md:w-3/5 mx-auto shadow-xl py-12"}
                   (d/div {:className "text-2xl w-full text-center py-6 border-b-2 border-lime-500"}
                        (:webTitle article-data))

                   (d/div {:className "mt-6 text-justify"}
                          (get-in article-data [:blocks :body 0 :bodyTextSummary])
                          )

                   ))))

(def Article (with-keechma ArticleRenderer))