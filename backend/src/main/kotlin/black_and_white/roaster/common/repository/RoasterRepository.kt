package black_and_white.roaster.common.repository

import black_and_white.roaster.common.model.entity.Roaster
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface RoasterRepository : CrudRepository<Roaster, Long>