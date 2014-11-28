(ns dash-api.todos.controllers-test
  (:require [clojure.test :refer :all]
            [ring.mock.request :as mock]
            [cheshire.core :refer :all]
            [dash-api.core.handler :refer :all]))

(deftest test-todo-api
  (testing "that GETing /api/todos returns a mapping between project titles and lists of todos."
    (let [response (app (mock/request :get "/api/todos"))]
      (is (= (:status response) 200)))))
