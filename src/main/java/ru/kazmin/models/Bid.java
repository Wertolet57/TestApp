package ru.kazmin.models;

import ru.kazmin.util.State;

import javax.persistence.*;
@Entity
@Table(name = "bid")
public class Bid {

        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        private Long id;

        @OneToOne(cascade = CascadeType.PERSIST)
        @JoinColumn(name = "user_id")
        private User user;

        @Column
        private State state;

        @Column
        private String essence;

}
