package emg.manning.liveproject.repository.impl;

import emg.manning.liveproject.repository.MerchantDetailsRepository;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Repository
public class InMemoryMerchantDetailsRepository implements MerchantDetailsRepository {
    private final Map<String, String> merchantDetails;

    public InMemoryMerchantDetailsRepository() {
        merchantDetails = new HashMap<>();
        merchantDetails.put("Acme", "acme-logo.png");
        merchantDetails.put("Globex", "globex-logo.png");
        merchantDetails.put("Morley", "morley-logo.png");
        merchantDetails.put("Contoso", "contoso-logo.png");
    }

    @Override
    public Optional<String> findLogoByMerchantId(final String merchantId) {
        return Optional.of(merchantDetails.getOrDefault(merchantId, "default-logo.png"));
    }
}
