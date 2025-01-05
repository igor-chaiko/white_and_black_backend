package black_and_white.roaster.common.service

import black_and_white.roaster.common.converter.convertToInfoDto
import black_and_white.roaster.common.converter.convertToShortDto
import black_and_white.roaster.common.model.dto.response.RoasterInfoDto
import black_and_white.roaster.common.model.dto.response.RoasterShortDto
import black_and_white.roaster.common.repository.RoasterRepository
import org.springframework.stereotype.Service

@Service
class RoasterServiceImpl(
    private val roasterRepository: RoasterRepository,
) : RoasterService {
    override fun getAllShort(): List<RoasterShortDto> =
        roasterRepository.findAll().map { it.convertToShortDto() }

    override fun getInfoById(id: Long): RoasterInfoDto {
        val roaster = roasterRepository.findById(id).orElseThrow{ NoSuchElementException("Такого обжарщика нет") }
        return roaster.convertToInfoDto()
    }
}