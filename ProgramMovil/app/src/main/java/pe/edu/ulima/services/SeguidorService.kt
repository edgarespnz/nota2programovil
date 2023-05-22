package pe.edu.ulima.services

import pe.edu.ulima.models.Imagen
import pe.edu.ulima.models.Seguidor
import pe.edu.ulima.models.Usuario

class SeguidorService {
    companion object {
        fun fetchAll(): List<Seguidor> {
            return listOf(
                Seguidor (1,1,2),
                Seguidor (2,1,3),
                Seguidor (3,1,4),
                Seguidor (4,1,5),
                Seguidor (5,1,6),
                Seguidor (6,1,7),
                Seguidor (7,2,1),
                Seguidor (8,2,4),
                Seguidor (9,2,5),
                Seguidor (10,2,6),
                Seguidor (11,2,7),
                Seguidor (12,3,1),
                Seguidor (13,3,2),
                Seguidor (14,3,4),
                Seguidor (15,3,10),
                Seguidor (16,4,8),
                Seguidor (17,4,9),
                Seguidor (18,4,10),
                Seguidor (19,4,1),
                Seguidor (20,5,1),
                Seguidor (21,5,2),
                Seguidor (22,5,3),
                Seguidor (23,5,4),
                Seguidor (24,5,6),
                Seguidor (25,5,7),
                Seguidor (26,5,8),
                Seguidor (27,5,9),
                Seguidor (28,5,10),
                Seguidor (29,6,1),
                Seguidor (30,6,7),
                Seguidor (31,6,3),
                Seguidor (32,6,10),
                Seguidor (33,7,1),
                Seguidor (34,7,3),
                Seguidor (35,7,6),
                Seguidor (36,7,8),
                Seguidor (37,7,9),
                Seguidor (38,8,1),
                Seguidor (39,8,2),
                Seguidor (40,8,3),
                Seguidor (41,8,4),
                Seguidor (42,9,5),
                Seguidor (43,9,6),
                Seguidor (44,10,1),
            )
        }

        fun countByUserId(userId: Int): Int{
            var respuesta : Int = 0
            val list: List<Seguidor> = SeguidorService.fetchAll()
            for(seguidor in list){
                if(seguidor.usuarioId == userId){
                    respuesta = respuesta + 1
                }
            }
            return respuesta
        }

        fun countSeguidosBySeId(userId: Int): Int{
            var respuesta : Int = 0
            val list: List<Seguidor> = SeguidorService.fetchAll()
            for(seguidor in list){
                if(seguidor.seguidorId == userId){
                    respuesta = respuesta + 1
                }
            }
            return respuesta
        }

        fun fetchSeguidores(userId: Int): List<Usuario>{
            val respuesta : MutableList<Usuario> = arrayListOf()
            val list: List<Seguidor> = SeguidorService.fetchAll()
            for(seguidor in list){
                if(seguidor.usuarioId == userId){
                    val seguidor: Usuario = UserService.fetchOne(seguidor.seguidorId)
                    respuesta.add(seguidor)
                }
            }
            return respuesta
        }
    }
}