
package schoolFinder;

class Request {
    private static int nextRequestId = 1;
    private int requestId;
    private Child child;
    private School school;
    private String status;

public Request(int requestId ,Child child, School school, String status) {
    this.requestId = requestId;
    this.child = child;
    this.school = school;
    this.status = status;
}
     //  method to generate the next request ID
    public static int generateRequestId() {
        return nextRequestId++;
    }
        public String toFormattedString() {
        return String.format("Request ID: %d Child ID: %d School: %s Status: %s", requestId, child.getChildId(), school.getName(), status);
    }

    public String toFormattedStringForFile() {
        return String.format("%d,%d,%s,%s", requestId, child.getChildId(), school.getName(), status);
    }
      public int getRequestId() {
        return requestId;
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