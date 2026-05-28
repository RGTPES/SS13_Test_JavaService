package re.edu.hackathon.exception;

public class NotFoundException extends RuntimeException {
    public NotFoundException(String mess){
        super(mess);
    }
}
