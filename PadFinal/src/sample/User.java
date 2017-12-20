package sample;

public class User {
    String firstname;
    String lastname;
    String phone;
    String etag;
    String id;
    String created;

    @Override
    public String toString() {
        return "User{" +
                "firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", phone='" + phone + '\'' +
                ", etag='" + etag + '\'' +
                ", id='" + id + '\'' +
                ", created='" + created + '\'' +
                '}';
    }

    public  User(){}

    public User(String firstname, String lastname, String phone, String etag, String id, String created) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.phone = phone;
        this.etag = etag;
        this.id = id;
        this.created = created;
    }
}
