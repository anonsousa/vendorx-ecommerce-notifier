package com.antoniosousa.ecommerce.notifier.repositories;

import com.antoniosousa.ecommerce.notifier.entities.VerificationToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface VerificationTokenRepository extends JpaRepository<VerificationToken, Long> {

    @Transactional
    @Modifying
    @Query("UPDATE VerificationToken v SET v.consumed = true WHERE v.id = :id")
    void updateConsumedById(@Param("id") long id);


    @Transactional(readOnly = true)
    List<VerificationToken> findAllByConsumedIsFalse();
}
