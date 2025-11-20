#!/bin/bash

# Advent of Code Input Fetcher
# Usage: ./fetch_input.sh <year> <day>
# Example: ./fetch_input.sh 2024 1

YEAR=$1
DAY=$2
SESSION_COOKIE=$3

if [ -z "$YEAR" ] || [ -z "$DAY" ]; then
    echo "Usage: $0 <year> <day> <session_cookie>"
    echo "Example: $0 2024 1 your_session_cookie_here"
    exit 1
fi

if [ -z "$SESSION_COOKIE" ]; then
    echo "Error: Session cookie is required"
    echo "Usage: $0 <year> <day> <session_cookie>"
    echo ""
    echo "To get your session cookie:"
    echo "1. Log into adventofcode.com"
    echo "2. Open browser dev tools (F12)"
    echo "3. Go to Application/Storage → Cookies → https://adventofcode.com"
    echo "4. Copy the value of the 'session' cookie"
    exit 1
fi

# Create directory if it doesn't exist
mkdir -p "${YEAR}/resources"

# Create temporary file to store response
TEMP_FILE=$(mktemp)
OUTPUT_FILE="${YEAR}/resources/Day$(printf "%02d" $DAY).txt"

# Fetch the input with error checking
HTTP_CODE=$(curl -s -w "%{http_code}" -H "Cookie: session=${SESSION_COOKIE}" \
     -H "User-Agent: github.com/Delni/advent-of-code" \
     "https://adventofcode.com/${YEAR}/day/${DAY}/input" \
     -o "$TEMP_FILE")

# Check if the request was successful
if [ "$HTTP_CODE" -eq 200 ]; then
    # Check if the response looks like HTML (error page)
    if grep -q "<!DOCTYPE html>" "$TEMP_FILE" || grep -q "<html>" "$TEMP_FILE"; then
        echo "Error: Received HTML response instead of input data"
        echo "This usually means:"
        echo "  - Invalid session cookie"
        echo "  - Day not yet available"
        echo "  - Already solved and input expired"
        echo "  - Rate limiting"
        rm "$TEMP_FILE"
        exit 1
    else
        # Move successful response to final location
        mv "$TEMP_FILE" "$OUTPUT_FILE"
        echo "✅ Downloaded input for ${YEAR} day ${DAY} to ${OUTPUT_FILE}"
        echo "📊 File size: $(wc -c < "$OUTPUT_FILE") bytes"
    fi
elif [ "$HTTP_CODE" -eq 400 ]; then
    echo "❌ Error 400: Bad request - check year ($YEAR) and day ($DAY) parameters"
    rm "$TEMP_FILE"
    exit 1
elif [ "$HTTP_CODE" -eq 404 ]; then
    echo "❌ Error 404: Day ${DAY} of year ${YEAR} not found or not yet available"
    rm "$TEMP_FILE"
    exit 1
elif [ "$HTTP_CODE" -eq 500 ]; then
    echo "❌ Error 500: Server error - try again later"
    rm "$TEMP_FILE"
    exit 1
else
    echo "❌ Error: HTTP $HTTP_CODE"
    echo "Response preview:"
    head -n 5 "$TEMP_FILE"
    rm "$TEMP_FILE"
    exit 1
fi

# Clean up
rm -f "$TEMP_FILE"