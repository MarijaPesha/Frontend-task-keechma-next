(ns app.api
  (:require [keechma.next.toolbox.ajax :refer [GET POST DELETE PUT]]
            [app.settings :refer [api-url api-key]]
            [promesa.core :as p]))

(def config {:keywords? true :response-format :json :format :json})

;; TASK DRY
(defn get-feed [req]
  (GET
    (str api-url "search?show-fields=thumbnail&q='tech','education','sport'&api-key=" api-key)
    config))

(defn get-tech [req]
  (GET
    (str api-url "search?show-fields=thumbnail&q='tech'&api-key=" api-key)
    config))

;; TASK WRONG API
(defn get-education [req]
  (GET
    (str api-url "search?show-fields=thumbnail&q='tech'&api-key=" api-key)
    config))

(defn get-sport [req]
  (GET
    (str api-url "search?show-fields=thumbnail&q='sport'&api-key=" api-key)
    config))

(defn get-article [article-id]
  (GET
    (str api-url article-id "?show-blocks=all&show-fields=body,thumbnail&api-key=" api-key)
    config))