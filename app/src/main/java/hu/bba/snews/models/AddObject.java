package hu.bba.snews.models;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class AddObject extends RealmObject {


    @PrimaryKey
    private String realmImageUrl;

    private String realmTitle;
    private String realmDesc;
    private String realmDate;
    private String realmCaption;
    private String realmEmail;

    public String getRealmTitle() {
        return realmTitle;
    }

    public void setRealmTitle(String realmTitle) {
        this.realmTitle = realmTitle;
    }

    public String getRealmDesc() {
        return realmDesc;
    }

    public void setRealmDesc(String realmDesc) {
        this.realmDesc = realmDesc;
    }

    public String getRealmDate() {
        return realmDate;
    }

    public void setRealmDate(String realmDate) {
        this.realmDate = realmDate;
    }

    public String getRealmImageUrl() {
        return realmImageUrl;
    }

    public void setRealmImageUrl(String realmImageUrl) {
        this.realmImageUrl = realmImageUrl;
    }

    public String getRealmCaption() {
        return realmCaption;
    }

    public void setRealmCaption(String realmCaption) {
        this.realmCaption = realmCaption;
    }

    public String getRealmEmail() {
        return realmEmail;
    }

    public void setRealmEmail(String realmEmail) {
        this.realmEmail = realmEmail;
    }
}
