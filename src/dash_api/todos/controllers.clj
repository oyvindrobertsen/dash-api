(ns dash-api.todos.controllers
  (:require [clojure.string :as str]))

(defn parse-todo
  "Parse a single todo."
  [[head & tail] state output]
  (partial parse-todo-token tail state (assoc output (:curr-proj state) (conj (get output (:curr-proj state)) head))))

(defn parse-todo-header
  "Parse a single project header."
  [[head & tail] state output]
  (partial parse-todo-token tail (assoc state :curr-proj head) (assoc output head [])))

(defn parse-todo-token
  "Parses a single token."
  [[head & tail] state output]
  (if head
    (condp re-seq head
      #"[a-zA-Z]+:" (partial parse-todo-header (cons head tail) state output)
      (partial parse-todo (cons head tail) state output))
    output))

(defn parse-todo-tokens
  "Parse a sequence of .todo tokens to a map of projects to lists of todos.
  Assumes a single level of nesting for now. Only todos contained withing a project will be included."
  [tokens]
  (let [state {}
        output {}]
    (trampoline parse-todo-token tokens state output)))

(defn tokenize
  "Tokenizer for .todo-files. One trimmed, non-empty line is a token."
  [file]
  (map str/trim (filter #(not (empty? %)) (str/split file #"\n"))))

(defn get-todos
  "Returns all todos currently in the .todo file specified in configuration. Strips empty lines."
  []
  (let [file (slurp "resources/todos/main.TODO")
        tokens (tokenize file)
        ret (parse-todo-tokens tokens)]
    ret))
