package es.upm.dit.isst.webLab.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.io.Serializable;
import java.util.Collection;

@Entity
public class Professor implements Serializable {
    @Id
    private String email;

    private String password;
    private String name;

    @OneToMany(mappedBy = "advisor", fetch = FetchType.EAGER)
    private Collection<TFG> advisedTFGs;

    public Professor() {

    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Collection<TFG> getAdvisedTFGs() {
        return advisedTFGs;
    }

    public void setAdvisedTFGs(Collection<TFG> advisedTFGs) {
        this.advisedTFGs = advisedTFGs;
    }
}
