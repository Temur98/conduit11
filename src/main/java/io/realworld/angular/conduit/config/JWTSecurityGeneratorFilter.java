package io.realworld.angular.conduit.config;


/*KERAK EMAS*/
/*
@Service
public class JWTSecurityGeneratorFilter extends OncePerRequestFilter {
    private final JWTUtility jwtUtility;

    public JWTSecurityGeneratorFilter(JWTUtility jwtUtility) {
        this.jwtUtility = jwtUtility;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            String jwtToken = jwtUtility.generate(authentication.getName(),
                    authentication.getAuthorities()
                            .stream()
                            .map(GrantedAuthority::getAuthority)
                            .collect(Collectors.joining(", "))
            );
            response.setHeader("jwt-authorization",jwtToken);
        }
        filterChain.doFilter(request,response);
    }
}

*/
