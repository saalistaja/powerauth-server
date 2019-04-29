/*
 * PowerAuth Server and related software components
 * Copyright (C) 2019 Wultra s.r.o.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published
 * by the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package io.getlime.security.powerauth.app.server.database.repository;

import io.getlime.security.powerauth.app.server.database.model.entity.RecoveryConfigEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

/**
 * Database repository for recovery config entities.
 *
 * @author Roman Strobl, roman.strobl@wultra.com
 */
@Component
public interface RecoveryConfigRepository extends CrudRepository<RecoveryConfigEntity, Long> {

    /**
     * Find recovery configuration for given application ID.
     * @param applicationId Application ID.
     * @return Recovery configuration.
     */
    RecoveryConfigEntity findByApplicationId(Long applicationId);

}