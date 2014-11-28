(ns dash-api.core.handler
  (:require [compojure.core :refer :all]
            [compojure.route :as route]
            [ring.middleware.defaults :refer [wrap-defaults api-defaults]]
            [ring.middleware.json :refer [wrap-json-response wrap-json-body]]
            [ring.util.response :refer [response]]
            [dash-api.todos.controllers :refer :all]
            [dash-api.todos.routes :refer :all]))

(def api-routes
  (defroutes api-context
    (GET "/" [] (response {:key "word"}))
    (context "/todos" [] todo-routes)))

(defroutes app-routes
  (context "/api" [] api-routes)
  (route/not-found "Not Found"))

(def app
  (wrap-json-response app-routes))
