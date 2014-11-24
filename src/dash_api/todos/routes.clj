(ns dash-api.todos.routes
  (:require [compojure.core :refer :all]
            [ring.util.response :refer [response]]
            [dash-api.todos.controllers :refer :all]))

(def todo-routes
  (defroutes todo-context
    (GET "/" [] (response (get-todos)))))
