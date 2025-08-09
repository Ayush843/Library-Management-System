public class Member {
    private int memberId;
    private String name;
    private String contact;
    private String email;

    public Member(int memberId, String name, String contact, String email) {
        this.memberId = memberId;
        this.name = name;
        this.contact = contact;
        this.email = email;
    }

    public int getMemberId() { return memberId; }
    public String getName() { return name; }
    public String getContact() { return contact; }
    public String getEmail() { return email; }
}
