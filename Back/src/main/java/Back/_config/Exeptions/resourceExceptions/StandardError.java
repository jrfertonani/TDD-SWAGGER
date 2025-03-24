package Back._config.Exeptions.resourceExceptions;

import java.io.Serializable;

public class StandardError implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long timesTamp;
    private Integer status;
    private String message;
    private String path;

    public StandardError() {
        super();
    }

    public StandardError(Long timesTamp, Integer status, String message, String path, String requestURI) {
        super();
        this.timesTamp = timesTamp;
        this.status = status;
        this.message = message;
        this.path = path;
    }

    public Long getTimesTamp() {
        return timesTamp;
    }

    public void setTimesTamp(Long timesTamp) {
        this.timesTamp = timesTamp;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
