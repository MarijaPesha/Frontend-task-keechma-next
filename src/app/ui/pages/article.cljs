(ns app.ui.pages.article
  (:require [helix.dom :as d]
            [helix.core :as hx :refer [$]]
            [keechma.next.helix.core :refer [with-keechma use-sub dispatch]]
            [keechma.next.controllers.router :as router]
            [keechma.next.helix.lib :refer [defnc]]
            [keechma.next.helix.classified :refer [defclassified]]
            [app.ui.components.navbar :refer [Navbar]]
            [app.ui.components.header :refer [Header]]
            [app.ui.components.footer :refer [Footer]]
            ))

(defclassified ArticleWrapper :div "font-open-sans h-screen w-screen flex flex-col bg-gray-200 dark:bg-gray-900 dark:text-gray-100 overflow-x-hidden overflow-y-scroll")

(defnc ArticleRenderer [props]
  (let [article-data (use-sub props :article)
        article-content (:content article-data)
        article-author (:author article-data)]
    ($ ArticleWrapper
      ($ Navbar)
      (d/div {:className "w-full md:w-3/5 mx-auto"}
        (d/img {:src       (get-in article-content [:fields :thumbnail])
                :className "object-cover w-full"}))
      (d/div {:class "dark:bg-gray-800 bg-gray-100 flex flex-1 flex-col items-start justify-start px-6 md:px-12 w-full md:w-3/5 mx-auto shadow-xl py-12"}
        (d/div {:className "text-2xl w-full text-center py-6 border-b-2 border-teal-500"}
          (:webTitle article-content))
             (d/p {:className "text-sm w-full text-center cursor-pointer hover:text-teal-500 py-3 border-b-2 border-teal-500"} (str "AUTHOR: " article-author))
        (d/div {:className "mt-6 text-justify"}
          (get-in article-content [:blocks :body 0 :bodyTextSummary])))
       ($ Footer)
       )))

(def Article (with-keechma ArticleRenderer))