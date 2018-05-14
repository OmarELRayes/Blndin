package com.example.android.blndin.Features.SquadProfile.Model;

import java.util.ArrayList;

/**
 * Created by LeGenÐ on 5/14/2018.
 */

public class SquadProfileDetailsResponse {
    String status;
    Payload payload;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Payload getPayload() {
        return payload;
    }

    public void setPayload(Payload payload) {
        this.payload = payload;
    }

    public class Payload {
        Squad squad;

        public Squad getSquad() {
            return squad;
        }

        public void setSquad(Squad squad) {
            this.squad = squad;
        }

        public class Squad {
            String title;
            String admin;
            String image;
            String description;
            ArrayList<Member> members;
            String created_at;

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getAdmin() {
                return admin;
            }

            public void setAdmin(String admin) {
                this.admin = admin;
            }

            public String getImage() {
                return image;
            }

            public void setImage(String image) {
                this.image = image;
            }

            public String getDescription() {
                return description;
            }

            public void setDescription(String description) {
                this.description = description;
            }

            public ArrayList<Member> getMembers() {
                return members;
            }

            public void setMembers(ArrayList<Member> members) {
                this.members = members;
            }

            public String getCreated_at() {
                return created_at;
            }

            public void setCreated_at(String created_at) {
                this.created_at = created_at;
            }

            public class Member {
                String uuid;
                String image;
                String username;

                public String getUuid() {
                    return uuid;
                }

                public void setUuid(String uuid) {
                    this.uuid = uuid;
                }

                public String getImage() {
                    return image;
                }

                public void setImage(String image) {
                    this.image = image;
                }

                public String getUsername() {
                    return username;
                }

                public void setUsername(String username) {
                    this.username = username;
                }
            }
        }
    }
}
