//package com.linguacards.app.model;
//
//import jakarta.persistence.Column;
//import jakarta.persistence.Entity;
//import jakarta.persistence.GeneratedValue;
//import jakarta.persistence.GenerationType;
//import jakarta.persistence.Id;
//import jakarta.persistence.JoinColumn;
//import jakarta.persistence.ManyToOne;
//import jakarta.persistence.Table;
//import lombok.Getter;
//import lombok.Setter;
//import org.apache.catalina.User;
//
//import java.time.LocalDateTime;
//
//@Entity
//@Table(name = "user_language_progress")
//@Getter
//@Setter
//public class UserLanguageProgress {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    @ManyToOne
//    @JoinColumn(name = "user_id", nullable = false)
//    private User user;
//
//    @ManyToOne
//    @JoinColumn(name = "language_id", nullable = false)
//    private Language language;
//
//    @Column(name = "learned_count")
//    private Integer learnedCount = 0;
//
//    @Column(name = "last_reviewed")
//    private LocalDateTime lastReviewed;
//}
