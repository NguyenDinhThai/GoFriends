package com.example.thainguyen.gofriends;

public class GroupFriends {
    private int idgroup;
    private String passgroup;

    public GroupFriends(int idgroup, String passgroup) {
        this.idgroup = idgroup;
        this.passgroup = passgroup;
    }

    public int getIdgroup() {
        return idgroup;
    }

    public void setIdgroup(int idgroup) {
        this.idgroup = idgroup;
    }

    public String getPassgroup() {
        return passgroup;
    }

    public void setPassgroup(String passgroup) {
        this.passgroup = passgroup;
    }

    @Override
    public String toString() {
        return "GroupFriends{" +
                "idgroup=" + idgroup +
                ", passgroup='" + passgroup + '\'' +
                '}';
    }
}
