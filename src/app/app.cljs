(ns app.app
  (:require [keechma.next.controllers.router]
            [keechma.next.controllers.dataloader]
            [keechma.next.controllers.subscription]
            [app.controllers.feed]
            [app.controllers.article]
            [app.controllers.article-tooltip]
            [app.controllers.theme]
            ["react-dom" :as rdom]))

(def app
  {:keechma.subscriptions/batcher rdom/unstable_batchedUpdates,
   :keechma/controllers           {:router     {:keechma.controller/params true
                                                :keechma.controller/type   :keechma/router
                                                :keechma/routes            [["" {:page "home"}] ":page" ":page/:subpage"]}
                                   :entitydb {:keechma.controller/params true
                                              :keechma.controller/type   :keechma/entitydb
                                              :keechma.entitydb/schema   {}}
                                   :dataloader {:keechma.controller/params true
                                                :keechma.controller/type   :keechma/dataloader}
                                   :feed       #:keechma.controller {:deps   [:entitydb :dataloader :router]
                                                                     :params (fn [{:keys [router]}]
                                                                               (when (= "home" (:page router))
                                                                                 (select-keys router [:page :subpage])))}
                                   :article    #:keechma.controller {:deps   [:entitydb :router]
                                                                     :params true}
                                   :article-tooltip    #:keechma.controller {:deps   [:entitydb :router]
                                                                             :params true}
                                   :theme      #:keechma.controller {:params true
                                                                     :deps   [:entitydb :router]}}})