package pe.edu.ulima.services

import pe.edu.ulima.models.Seguido

class SeguidoService {
    companion object {
        fun fetchAll(): List<Seguido> {
            return listOf(
                Seguido (1,1,2),
                Seguido (2,1,3),
                Seguido (3,1,4),
                Seguido (4,1,5),
                Seguido (5,1,6),
                Seguido (6,1,7),
                Seguido (7,2,1),
                Seguido (8,2,4),
                Seguido (9,2,5),
                Seguido (10,2,6),
                Seguido (11,2,7),
                Seguido (12,3,1),
                Seguido (13,3,2),
                Seguido (14,3,4),
                Seguido (15,3,10),
                Seguido (16,4,8),
                Seguido (17,4,9),
                Seguido (18,4,10),
                Seguido (19,4,1),
                Seguido (20,5,1),
                Seguido (21,5,2),
                Seguido (22,5,3),
                Seguido (23,5,4),
                Seguido (24,5,6),
                Seguido (25,5,7),
                Seguido (26,5,8),
                Seguido (27,5,9),
                Seguido (28,5,10),
                Seguido (29,6,1),
                Seguido (30,6,7),
                Seguido (31,6,3),
                Seguido (32,6,10),
                Seguido (33,7,1),
                Seguido (34,7,3),
                Seguido (35,7,6),
                Seguido (36,7,8),
                Seguido (37,7,9),
                Seguido (38,8,1),
                Seguido (39,8,2),
                Seguido (40,8,3),
                Seguido (41,8,4),
                Seguido (42,9,5),
                Seguido (43,9,6),
                Seguido (44,10,1),
            )
        }
    }
}