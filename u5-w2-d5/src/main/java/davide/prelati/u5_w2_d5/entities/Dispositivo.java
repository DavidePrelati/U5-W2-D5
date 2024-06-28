package davide.prelati.u5_w2_d5.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Dispositivo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String type;
    @Enumerated(EnumType.STRING)
    private StatoDispositivo status;
    @ManyToOne
    @JoinColumn(name = "id_dipendente")
    private Dipendente dipendente;

}


