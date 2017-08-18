package models;

import com.avaje.ebean.Expr;
import play.db.ebean.Model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Entity
public class Owned extends Model {
	@Id
	public Long id;
    public String deleteStatus = "";
	
	@ManyToOne(cascade = CascadeType.ALL)
    public Users owner;

	

	@ManyToOne(cascade = CascadeType.ALL)
    public Projects project;

    public static Model.Finder<Long, Owned> find = new Model.Finder<Long, Owned>(Long.class, Owned.class);
    public static List<Owned> all(){
        return find.where().not(Expr.eq("deleteStatus", "1")).findList();
    }
    public static List<Owned> finderByFk( Long id ){
        return find.where().like("owner",String.valueOf(id)).findList();
    }
    public static Owned finderById(long id){
        return find.ref(id);
    }
}