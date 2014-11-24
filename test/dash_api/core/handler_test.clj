(ns dash-api.core.handler-test
  (:require [clojure.test :refer :all]
            [ring.mock.request :as mock]
            [cheshire.core :refer :all]
            [dash-api.core.handler :refer :all]))

(deftest test-app
  (testing "root api route"
    (let [response (app (mock/request :get "/api"))]
      (is (= (:status response) 200))
      (is (= (:body response) (generate-string {:key "word"})))))
  
  (testing "not-found route"
    (let [response (app (mock/request :get "/invalid"))]
      (is (= (:status response) 404)))))

(deftest test-todo-api
  (testing "that GETing /api/todos returns a list of todos"
    (let [response (app (mock/request :get "/api/todos"))]
      (is (= (:status response) 200))
      (is (= (:body response) (generate-string [{:this "is the list of todos"} {:it "is pretty empty"}]))))))
