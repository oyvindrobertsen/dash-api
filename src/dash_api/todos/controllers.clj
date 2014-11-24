(ns dash-api.todos.controllers)

(defn get-todos
  "Returns all todos currently in the .todo file specified in configuration."
  []
  [{:this "is the list of todos"} {:it "is pretty empty"}])
