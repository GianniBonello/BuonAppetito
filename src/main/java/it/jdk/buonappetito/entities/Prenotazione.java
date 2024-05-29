//package it.jdk.buonappetito.entities;
//
//import jakarta.persistence.*;
//import lombok.*;
//
//import java.time.LocalDateTime;
//import java.util.List;
//
//@Entity
//@Getter
//@Setter
//@NoArgsConstructor
//@AllArgsConstructor
//@Builder
//public class Prenotazione {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    @ManyToOne
//    private Utente utente;
//
//    @ManyToOne
//    private Ristorante ristorante;
//
//    private LocalDateTime dataOraPrenotazione;
//
//    private boolean pagato;
//
//    @ManyToMany
//    @JoinTable(
//            name = "prenotazione_posti",
//            joinColumns = @JoinColumn(name = "prenotazione_id"),
//            inverseJoinColumns = @JoinColumn(name = "posto_id")
//    )
//    private List<Posto> posti;
//}
