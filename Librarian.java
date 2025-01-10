public class Librarian {

    String _librarianName;
    String _librarianPassword;

    public Librarian(String librarianName, String librarianPassword){
        this._librarianName = librarianName;
        this._librarianPassword = librarianPassword;
    }

    public boolean librarianLogin(String password){
        if (password.equals(_librarianPassword)) {
            System.out.println("\nGood evening, " + _librarianName);
            return true;
        }
        else {
            System.out.println("Login unsuccessful.");
            return false;
        }
    }

    public String getLibrarianName() {
        return this._librarianName;
    }
}
