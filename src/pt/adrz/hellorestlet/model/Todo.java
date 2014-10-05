package pt.adrz.hellorestlet.model;

public class Todo {

	private Integer id;
    private String 	title;
    private String 	description;
    private boolean done;

    public Todo() { }

    public Todo(Integer id, String title, String description) {

        this.id = id;
        this.title = title;
        this.description = description;
        this.done = false;
    }

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public boolean isDone() { return done; }
    public void setDone(boolean done) { this.done = done; }

    @Override
    public boolean equals(Object o) {
        if ( this == o ) return true;
        if ( o == null || getClass() != o.getClass() ) return false;
        Todo todo = (Todo) o;
        //return this.id == todo.getId();
        return id.equals(todo.id);
    }

    @Override
    public int hashCode() { return id.hashCode(); }
}
