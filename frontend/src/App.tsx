import React, { useState } from 'react'
import { SmsForm } from './components/SmsForm'
import { SmsPartsDisplay } from './components/SmsPartsDisplay'
import './App.css'

function App() {
  const [smsParts, setSmsParts] = useState<string[]>([])

  const handleSmsParts = (parts: string[]) => {
    setSmsParts(parts)
  }

  return (
    <div className="min-h-screen bg-gradient-to-br from-blue-500 via-purple-600 to-pink-500 relative overflow-hidden">
      {/* Animated Background Elements */}
      <div className="absolute inset-0 overflow-hidden">
        <div className="absolute -top-40 -right-40 w-80 h-80 bg-white/10 rounded-full blur-3xl animate-pulse"></div>
        <div className="absolute -bottom-40 -left-40 w-80 h-80 bg-white/10 rounded-full blur-3xl animate-pulse delay-1000"></div>
        <div className="absolute top-1/2 left-1/2 transform -translate-x-1/2 -translate-y-1/2 w-96 h-96 bg-white/5 rounded-full blur-3xl animate-pulse delay-500"></div>
      </div>

      {/* Content */}
      <div className="relative z-10 min-h-screen flex flex-col">
        {/* Header */}
        <header className="text-center py-12 px-4">
          <h1 className="text-5xl md:text-7xl font-bold text-white mb-4 tracking-tight">
            <span className="bg-gradient-to-r from-white to-blue-200 bg-clip-text text-transparent">
              SMS Splitter
            </span>
          </h1>
          <p className="text-xl text-white/80 max-w-2xl mx-auto leading-relaxed">
            Split your long messages into SMS-compliant parts with our modern API versioning system
          </p>
        </header>

        {/* Main Content */}
        <main className="flex-1 px-4 pb-12">
          <div className="max-w-6xl mx-auto">
            {/* SMS Form */}
            <div className="flex justify-center mb-8">
              <SmsForm onSmsParts={handleSmsParts} />
            </div>

            {/* SMS Parts Display */}
            <SmsPartsDisplay parts={smsParts} />
          </div>
        </main>

        {/* Footer */}
        <footer className="text-center py-8 px-4">
          <div className="text-white/60 text-sm">
            <p className="mb-2">
              Powered by 
            </p>
            <div className="flex items-center justify-center space-x-4">
              <span className="px-3 py-1 bg-white/10 rounded-full text-xs">
                React
              </span>
              <span className="px-3 py-1 bg-white/10 rounded-full text-xs">
                Spring Boot
              </span>
            </div>
          </div>
        </footer>
      </div>
    </div>
  )
}

export default App
