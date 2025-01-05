package black_and_white.roaster.common.service

import black_and_white.roaster.common.model.dto.response.RoasterInfoDto
import black_and_white.roaster.common.model.dto.response.RoasterShortDto

interface RoasterService {
    fun getAllShort(): List<RoasterShortDto>

    fun getInfoById(id: Long): RoasterInfoDto
}