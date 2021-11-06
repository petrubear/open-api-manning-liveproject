package emg.manning.liveproject.repository;

import java.util.Optional;

public interface MerchantDetailsRepository {
    Optional<String> findLogoByMerchantId(final String merchantName);
}
