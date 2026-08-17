import { useEffect } from "react";
import api from "../services/api";

function TestAPI() {
    useEffect(() => {
        api.get("/patient/queue/1/current")
            .then((response) => {
                console.log("API Response:", response.data);
            })
            .catch((error) => {
                console.error("API Error:", error);
            });
    }, []);

    return (
        <div>
            <h2>QueLess API Test</h2>
            <p>Check the browser console for the API response.</p>
        </div>
    );
}

export default TestAPI;