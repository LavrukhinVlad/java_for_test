package model;

public record ContactData(String firstname, String middlename, String lastname, String nickname, String title,
                          String company, String address, String home, String mobile, String work, String fax,
                          String email, String email2, String email3) {

    public ContactData(){
        this("","","","","","","","","","","","","","");
    }

    public ContactData withFirstname(String firstname) {
        return new ContactData(firstname, this.middlename, this.lastname, this.nickname, this.title, this.company, this.address, this.home, this.mobile, this.work, this.fax, this.email, this.email2, this.email3);
    }

    public ContactData withLastname(String lastname) {
        return new ContactData(this.firstname, this.middlename, lastname, this.nickname, this.title, this.company, this.address, this.home, this.mobile, this.work, this.fax, this.email, this.email2, this.email3);
    }

    public ContactData withAddress(String address) {
        return new ContactData(this.firstname, this.middlename, this.lastname, this.nickname, this.title, this.company, address, this.home, this.mobile, this.work, this.fax, this.email, this.email2, this.email3);
    }


    public ContactData withEmail(String email) {
        return new ContactData(this.firstname, this.middlename, this.lastname, this.nickname, this.title, this.company, this.address, this.home, this.mobile, this.work, this.fax, email, this.email2, this.email3);
    }


    public ContactData withMobile(String mobile) {
        return new ContactData(this.firstname, this.middlename, this.lastname, this.nickname, this.title, this.company, this.address, this.home, mobile, this.work, this.fax, this.email, this.email2, this.email3);
    }
}
