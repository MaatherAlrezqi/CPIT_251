
package schoolFinder;

class Request {
    private Child child;
    private School school;
    private String status;

    public Request(Child child, School school, String status) {
        this.child = child;
        this.school = school;
        this.status = status;
    }

    public Child getChild() {
        return child;
    }

    public void setChild(Child child) {
        this.child = child;
    }

    public School getSchool() {
        return school;
    }

    public void setSchool(School school) {
        this.school = school;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}