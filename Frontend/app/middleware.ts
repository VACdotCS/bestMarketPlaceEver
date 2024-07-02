import { NextRequest, NextResponse } from "next/server";

export function middleware(req: NextRequest) {
    const token = req.cookies.get("token");
    const { pathname } = req.nextUrl;

    // Если текущий путь уже /auth, не перенаправляем снова на /auth
    if (!token && pathname !== "/auth") {
        const url = req.nextUrl.clone();
        url.pathname = "/auth";
        return NextResponse.redirect(url);
    }

    console.log("token", token);
    return NextResponse.next();
}

export const config = {
    matcher: [
        /*
         * Match all request paths except for the ones starting with:
         * - api (API routes)
         * - _next/static (static files)
         * - _next/image (image optimization files)
         * - favicon.ico (favicon file)
         */
        "/((?!auth|api|_next/static|_next/image|favicon.ico).*)",
    ],
};
