-- error_codes seed data
-- ON CONFLICT DO NOTHING makes this safe to re-run on every startup
INSERT INTO public.error_codes (code, name, description)
VALUES (1001, 'INVALID_CREDENTIALS', 'The provided username or password is incorrect.')
ON CONFLICT (name) DO NOTHING;

INSERT INTO public.error_codes (code, name, description)
VALUES (1002, 'USER_NOT_FOUND', 'No account found with the provided email address.')
ON CONFLICT (name) DO NOTHING;

INSERT INTO public.error_codes (code, name, description)
VALUES (1003, 'USER_ALREADY_EXISTS', 'An account with this email address already exists.')
ON CONFLICT (name) DO NOTHING;

INSERT INTO public.error_codes (code, name, description)
VALUES (1004, 'ACCESS_DENIED', 'You do not have permission to access this resource.')
ON CONFLICT (name) DO NOTHING;
